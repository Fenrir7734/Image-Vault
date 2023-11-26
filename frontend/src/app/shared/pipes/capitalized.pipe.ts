import { Pipe, PipeTransform } from '@angular/core';
import { capitalize } from 'lodash';

@Pipe({
  name: 'capitalized',
})
export class CapitalizedPipe implements PipeTransform {
  transform(value: string): unknown {
    return capitalize(value);
  }
}
